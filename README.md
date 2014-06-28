#Remote Method Invocation
===========

##Objective

In this project, we emulated the behavior of the java.rmi package. In general, we tried to allow objects in one Java Virtual Machine (JVM) to be invoked from another JVM.

![alt tag](https://raw.githubusercontent.com/Jeremy-Fu/remote-method-invocation/master/architecture.png?token=5368021__eyJzY29wZSI6IlJhd0Jsb2I6SmVyZW15LUZ1L3JlbW90ZS1tZXRob2QtaW52b2NhdGlvbi9tYXN0ZXIvYXJjaGl0ZWN0dXJlLnBuZyIsImV4cGlyZXMiOjE0MDQ1NzIwNjF9--9dd07078843ba9c46693020d1b83818dd9779d66)

##Application Programming Interface

We followed the java.rmi program paradigm to design our packages. From an application programmer’s perspective, the remote object needs to be instantiated, exported and rebound in order to make it invoked remotely. 

A remote interface which extends the Remote440 interface is required. The remote interface defines the methods that could be invoked remotely. Then the implementation class needs to implement the remote interface. The implementation of classes could defines both local and remote methods but only remote methods will be exposed to be invoked remotely. After an object is instantiated from a class which implements remote interface, it needs to be exported and obtains the corresponding stub. It is worth to mention that a remote object is not necessary to be rebound on RMI registry because some remote objects can be instantiated by other remote objects and the stub can be passed via return value, which also means that the stub won’t necessarily be obtained via RMI registry.

In our design, once a remote object is exported, it is registered on a proxy dispatcher that listens for incoming invocations and distributes the invocations to different exported remote objects on the given listening port. And the stub also implements the same interface as remote object implements which ensures the client can call such methods remotely. But as a stub, it doesn’t perform the function. Instead, it marshalls the parameters, sends the invocation to the remote host and unmarshalls the return value. 

Our design is different from the paradigm provided in Honda’s and we choose to emulate how Java.rmi packages provide to application programmers. This is because under Java’s paradigm, it provides more flexibility. For example, application programmers can decide whether and/or when to export a remote object. And it is more flexible to manage the remote objects as well. 

##Components
###RMIregistry

RMIregistry is a server. It maps the service name to the stub of remote object. A stub of RMIregistry is instantiated when called by static methods of LocateRegistry440 along with RMI registry host and port. The RMIregistry stub communicates with RMIregistry server to get results of requests back. The RMIregistry server accepts and handles request both from server and client side. It accepts five kinds of request:
```
* Handshake: Handshake request is used to detect the availability of RMIregistry in LocateRegistry440 class. It is reserved for further implementation of security as well. For example, a server could restrict the access availability to a set of hosts and deny requests from other hosts. Distinguished from the design of Java’s LocateRegistry, where a RMIregistry stub is instantiated while no communication occurs, in our design, the getRegistry() of LocateRegistry440 sends the Handshake message in the process of instantiating the RMIregistry stub. Some exceptions may be thrown in situations such as host is not reachable, security policy restricts the access. We believe it is more secure to check the availability of RMI registry before sends messages with essential parameters.
* Rebind: Rebind request is to rebind the service name to the stub on RMIregistry. If the service name has been used, the corresponding stub will also be updated without warning. We leave the responsibility to users that naming service globally. The philosophy of this design is based on that RMIregistry is running on the server side and we leave the flexibility to application programmers to handle such situation.
* Unbind: Unbind request is used to inform the RMIregistry server to delete the corresponding service name and stub information that had been registered on. Once a service is unbound, the stub of it will not be available on RMIregistry anymore. If the service requested is not available on registry, a NotBoundException will be thrown.
* List: List request gets back all the services names registered on RMIregistry currently.
* Lookup: A Lookup request looks for a specific service on RMIregistry server. The RMIregistry will then return the associate stub of that service back to client. If the service requested is not available on registry, a NotBoundException will be thrown.
```

