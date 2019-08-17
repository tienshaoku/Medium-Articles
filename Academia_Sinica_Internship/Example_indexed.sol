pragma solidity >= 0.4.21;

contract ExampleContract{
    
    mapping (string => uint) balanceByName;
    //event setBalanceEvent(string, uint);
    event setBalanceEvent(string indexed name, uint indexed balance);
    
    function setBalance(string memory _name, uint _balance) public {
        balanceByName[_name] = _balance;
        emit setBalanceEvent(_name, _balance);
    }
    
    function returnBalance(string memory _name) public view returns(uint){
        return balanceByName[_name];
    }
    
}