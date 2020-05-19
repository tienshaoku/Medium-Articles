pragma solidity >= 0.5.0;

import "IUniswapV2Router01.sol";
//import "IUniswapV2Factory.sol";
import "SafeMath.sol";
import "ERC20.sol";
import "WETH.sol";
import "DaiCERC20.sol";

contract UniswapImplementation{
    // Pair address: 0x03E6c12eF405AC3F642B9184eDed8E1322de1a9e
    // IUniswapV2Factory uniswapV2Factory;
    IUniswapV2Router01 immutable uniswapV2Router01;
    ERC20 immutable Dai;
    CErc20 immutable cDai;
    address public WETHAddress;
    address public DaiAddress;
    address public cDaiAddress;
    uint public DaiAmount;
    uint public ETHAmount;
    uint[3] public addLiquidityResult;
    
    using SafeMath for uint;
    
    constructor () public {
        WETHAddress = 0xc778417E063141139Fce010982780140Aa0cD5Ab;
        DaiAddress = 0x5592EC0cfb4dbc12D3aB100b257153436a1f0FEa;
        cDaiAddress = 0x6D7F0754FFeb405d23C51CE938289d4835bE3b14;
        Dai = ERC20(DaiAddress);
        cDai = CErc20(cDaiAddress);
        // uniswapV2Factory = IUniswapV2Factory(0x5C69bEe701ef814a2B6a3EDD4B1652CB9cc5aA6f);
        uniswapV2Router01 = IUniswapV2Router01(0xf164fC0Ec4E93095b804a4795bBe1e041497b92a);
        // Let's say the initial supply in liquidity pool is 1 ETH and 200 Dai
        DaiAmount = 200000000000000000000;
        ETHAmount = 1 ether; // 1000000000000000000
    }

    
    function addLiquidity() public payable returns(uint[3] memory) {
        require(msg.value == ETHAmount);
        require(Dai.transferFrom(msg.sender, address(this), DaiAmount));
        // Do remember to approve
        Dai.approve(address(uniswapV2Router01), DaiAmount);
        (addLiquidityResult[0], addLiquidityResult[1], addLiquidityResult[2]) = 
        uniswapV2Router01.addLiquidityETH{value: ETHAmount}(DaiAddress, DaiAmount, 0, 0, msg.sender, now + 120);
        return addLiquidityResult;
    }
    
    function swapDaiForETH(uint _DaiAmount) public returns(uint[] memory) {
        require(Dai.transferFrom(msg.sender, address(this), _DaiAmount));
        address[] memory _paths = new address[](2);
        _paths[0] = DaiAddress;
        _paths[1] = WETHAddress;
        Dai.approve(address(uniswapV2Router01), _DaiAmount);
        return uniswapV2Router01.swapExactTokensForETH(_DaiAmount, 0, _paths, msg.sender, now + 120);
    }
    

    function swapETHToDai() public payable returns(uint[] memory) {
        // static array: 
        // address[k] memory array;
        // The following is the dynamic array way of initialization
        address[] memory _paths = new address[](2);
        // Also, push() is for storage array.
        _paths[0] = WETHAddress;
        _paths[1] = DaiAddress;
        return uniswapV2Router01.swapExactETHForTokens{value: msg.value}(0, _paths, msg.sender, now + 120);
    }
    
    
    function swapETHToDaiToCompound() public payable returns(bool){
        address[] memory _paths = new address[](2);
        _paths[0] = WETHAddress;
        _paths[1] = DaiAddress;
        uint[] memory _result = uniswapV2Router01.swapExactETHForTokens{value: msg.value}(0, _paths, address(this), now + 120);
        uint _DaiAmount = _result[1];
        Dai.approve(cDaiAddress, _DaiAmount); 
        assert(cDai.mint(_DaiAmount) == 0);
        return true;
    }
    
    function redeemFromCompoundAndReturnETH() public returns(uint[] memory){
        assert(cDai.redeem(cDai.balanceOf(address(this))) == 0);
        address[] memory _paths = new address[](2);
        _paths[0] = DaiAddress;
        _paths[1] = WETHAddress;
        uint _DaiAmount = Dai.balanceOf(address(this));
        Dai.approve(address(uniswapV2Router01), _DaiAmount);
        return uniswapV2Router01.swapExactTokensForETH(_DaiAmount, 0, _paths, msg.sender, now + 120);
    }
    
    function kill() public {
        selfdestruct(msg.sender);
    }
    
    
}
