package test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class ExampleContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061039d806100206000396000f3fe608060405260043610610046576000357c010000000000000000000000000000000000000000000000000000000090048063361ab84f1461004b57806338639f7b14610127575b600080fd5b34801561005757600080fd5b506101116004803603602081101561006e57600080fd5b810190808035906020019064010000000081111561008b57600080fd5b82018360208201111561009d57600080fd5b803590602001918460018302840111640100000000831117156100bf57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506101f9565b6040518082815260200191505060405180910390f35b34801561013357600080fd5b506101f76004803603604081101561014a57600080fd5b810190808035906020019064010000000081111561016757600080fd5b82018360208201111561017957600080fd5b8035906020019184600183028401116401000000008311171561019b57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019092919050505061026d565b005b600080826040518082805190602001908083835b602083101515610232578051825260208201915060208101905060208303925061020d565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020549050919050565b806000836040518082805190602001908083835b6020831015156102a65780518252602082019150602081019050602083039250610281565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390208190555080826040518082805190602001908083835b60208310151561031457805182526020820191506020810190506020830392506102ef565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207f11fa40d38d8c12e6885f25dce1bff58b84fb756b4fbf3d70be7237df8c4569bc60405160405180910390a3505056fea165627a7a723058207d3c980c2a7b8ba7083467143876b9125a9b5fb08703f4c4dd3f380b5929060f0029";

    public static final String FUNC_RETURNBALANCE = "returnBalance";

    public static final String FUNC_SETBALANCE = "setBalance";

    public static final Event SETBALANCEEVENT_EVENT = new Event("setBalanceEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    protected ExampleContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ExampleContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> returnBalance(String _name) {
        final Function function = new Function(FUNC_RETURNBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setBalance(String _name, BigInteger _balance) {
        final Function function = new Function(
                FUNC_SETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(_balance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<SetBalanceEventEventResponse> getSetBalanceEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SETBALANCEEVENT_EVENT, transactionReceipt);
        ArrayList<SetBalanceEventEventResponse> responses = new ArrayList<SetBalanceEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SetBalanceEventEventResponse typedResponse = new SetBalanceEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.balance = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SetBalanceEventEventResponse> setBalanceEventEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SetBalanceEventEventResponse>() {
            @Override
            public SetBalanceEventEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SETBALANCEEVENT_EVENT, log);
                SetBalanceEventEventResponse typedResponse = new SetBalanceEventEventResponse();
                typedResponse.log = log;
                typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.balance = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SetBalanceEventEventResponse> setBalanceEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETBALANCEEVENT_EVENT));
        return setBalanceEventEventObservable(filter);
    }

    public static RemoteCall<ExampleContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ExampleContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ExampleContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ExampleContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static ExampleContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ExampleContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ExampleContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ExampleContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SetBalanceEventEventResponse {
        public Log log;

        public byte[] name;

        public BigInteger balance;
    }
}
