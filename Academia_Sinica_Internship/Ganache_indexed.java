import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import rx.Subscription;
import tran.ACLCONTRACT;
import tran.ExampleContract;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class Main {
    private final static String PRIVATE_KEY = "140b77c09ccb49638f2cf9360680ea5a84f48a6f104952a833594d349694d3a9";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L); // L being long; capital for clarity

    public static void main(String[] args){
        try {
            new Main();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private Main() throws Exception {
        Web3j web3j = Web3j.build(new HttpService());
        printWeb3Version(web3j);

        Credentials credentials = getCredentialsFromPrivateKey();
        System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());

        String deployedContract = deployContract(web3j, credentials);
        System.out.println("Deployed contract address; " + deployedContract);

        ExampleContract exampleContract = loadContract(deployedContract, web3j, credentials);
        activateFilter(web3j, deployedContract, exampleContract);

        exampleContract.setBalance("Alice", BigInteger.valueOf(4)).send();
        System.out.println("----------------------------------------");
        Object ans = exampleContract.returnBalance("Alice").send();
        System.out.println("user-defined message:   " + ans.toString());
        ans = exampleContract.returnBalance("Bob").send();
        System.out.println("user-defined message:   " + ans.toString());
        System.out.println();
    }

    private void printWeb3Version(Web3j web3j){
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String web3ClientVersionString = web3ClientVersion.getWeb3ClientVersion();
        System.out.println();
        System.out.println("Web3 client version: " + web3ClientVersionString);
        System.out.println();
    }

    private Credentials getCredentialsFromPrivateKey(){
        return Credentials.create(PRIVATE_KEY);
    }

    private Credentials getCredentialsFromPrivateKeyAddress() throws  Exception {
        Credentials credentials = WalletUtils.loadCredentials("<password>", "<credential-directory>");
        return credentials;
    }

    private String deployContract(Web3j web3j, Credentials credentials) throws Exception{
        return ExampleContract.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .send()
                .getContractAddress();
    }

    private ExampleContract loadContract(String address, Web3j web3j, Credentials credentials){
        return ExampleContract.load(address, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }
	
	private void activateFilter(Web3j web3j, String contractAddress, ExampleContract exampleContract){
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, contractAddress);
        exampleContract.setBalanceEventEventObservable(filter).subscribe(log-> System.out.println(log.toString()));
        exampleContract.setBalanceEventEventObservable(filter).subscribe(log-> System.out.println(log.balance));
        web3j.ethLogObservable(filter).subscribe(log -> System.out.println(log.toString()));
    }
}
