package example.compute;

import ror.Remote440;
import exception.RemoteException440;

public interface ComputeInterface extends Remote440 {
    <T> T executeTask(Task<T> t) throws RemoteException440;
}

