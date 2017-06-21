A very basic chat application. It runs inside a client's JVM, so it is cross-platform.

Commands to COMPILE the source files:

Before compilation, make a directory /home/username/public_html/classes 

(assuming you are in your project's root directory)

Compile Stucture (the interfaces class)

For Microsoft Windows

cd c:\home\username\src
javac compute\Compute.java compute\Task.java
jar cvf compute.jar compute\*.class
For Microsoft Windows

For Linux/Solaris

cd /home/waldo/src
javac compute/Compute.java compute/Task.java
jar cvf compute.jar compute/*.class

Compile the server files

For Microsoft Windows

cd c:\home\username\src
javac -cp c:\home\username\public_html\classes\structure.jar
    GroupChat\GroupChat.java

For Linux/Solaris

cd /home/username/src
javac -cp /home/username/public_html/classes/structure.jar
    GroupChat/GroupChat.java

Compile the client files

For Microsoft Windows

cd c:\home\jones\src
javac -cp c:\home\jones\public_html\classes\compute.jar
    client\ComputePi.java client\Pi.java
mkdir c:\home\jones\public_html\classes\client
cp client\Pi.class
    c:\home\jones\public_html\classes\client

For Linux/Solaris

cd /home/jones/src
javac -cp /home/jones/public_html/classes/compute.jar
    client/ComputePi.java client/Pi.java
mkdir /home/jones/public_html/classes/client
cp client/Pi.class
    /home/jones/public_html/classes/client
    STARTING THE SERVER:

Microsoft Windows (use javaw if start is not available):

start rmiregistry

Solaris OS or Linux:

rmiregistry &

Microsoft Windows:

java -cp c:\home\ann\src;c:\home\ann\public_html\classes\compute.jar
     -Djava.rmi.server.codebase=file:/c:/home/ann/public_html/classes/compute.jar
     -Djava.rmi.server.hostname=mycomputer.example.com
     -Djava.security.policy=server.policy
        engine.ComputeEngine

Solaris OS or Linux:

java -cp /home/ann/src:/home/ann/public_html/classes/compute.jar
     -Djava.rmi.server.codebase=http://mycomputer/~ann/classes/compute.jar
     -Djava.rmi.server.hostname=mycomputer.example.com
     -Djava.security.policy=server.policy
        engine.ComputeEngine

STARTING THE CLIENT:

Microsoft Windows:

java -cp c:\home\jones\src;c:\home\jones\public_html\classes\compute.jar
     -Djava.rmi.server.codebase=file:/c:/home/jones/public_html/classes/
     -Djava.security.policy=client.policy
        client.ComputePi mycomputer.example.com 45

Solaris OS or Linux:

java -cp /home/jones/src:/home/jones/public_html/classes/compute.jar
     -Djava.rmi.server.codebase=http://mysecondcomputer/~jones/classes/
     -Djava.security.policy=client.policy
        client.ComputePi mycomputer.example.com 45
