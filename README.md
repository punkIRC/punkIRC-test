# Project Pricefield
[![Build Status](https://dev.azure.com/rubenmaurer/Project%20Pricefield/_apis/build/status/Pricefield%20CI)](https://dev.azure.com/rubenmaurer/Project%20Pricefield/_build/latest?definitionId=6)

IRC test-application loosely based on the [chirc project](https://github.com/uchicago-cs/chirc).

## Getting Started
The latest release can be found [here](https://github.com/punkIRC/punkIRC-test/releases).

### Setup Source
When compiling the project by yourself you need Maven installed.

```
$ git clone https://github.com/punkIRC/punkIRC-test
$ cd punkIRC-test
$ mvn package
```

This should download the latest version, install all dependencies and create a runnable .jar at ``` punkIRC-test/target/pricefield ```.

## Configuration
In the root folder where the ``` priceIRC.jar ``` is placed, you also find the ``` config.properties ```.

```
executable=chirc        # Path to your server executable
parameter_0=-o foobar   # First (possible) parameter for your server
parameter_1=-vv         # Second (possible) parameter for your server
parameter_2=            # Third (possible) parameter for your server

hostname=localhost      # The hostname/ ip to connect to
port=6667               # The port your server is listening on

java=true               # The server is written in java?
                        # aka is a packed *.jar file?
                        # aka called 'java mode'

timeout=3               # Seconds priceIRC waits for an answer of your server
                        # After n seconds the test will proceed, even without
                        # a complete response.

startDelay=5            # Seconds to wait until your server is fully operational
                        # This value highly  depends on your server. If your server has many startup checks
                        # build in you probably  should increase the delay.

stopDelay=2             # Seconds to wait until your server is fully offline
                        # Same as above.

authLines=10            # The amount of lines your server sent during authentication
                        # A 'perfect' server sends 10 lines during authentication.                   
```

Most of the values, probably can left untouched.

## Parameter
The ``` config.properties ``` file is not the only way to control ```priceIRC```. Most of the settings can
adjusted by command line parameter.

##### Server executable
```cmd
--executable="<server_path>
-e="<server_path>
```

##### Server port
```cmd
--port="<server_port>
-p="<server_port>
```

##### Server hostname
```cmd
--host="<server_host>
-h="<server_host>
```

##### Deactivate java mode
```cmd
--noJava
-nj
```

##### Force use of java mode
```cmd
--java
-j
```

##### Create a JUnit report
```cmd
--report
-r
```

##### Decide which tests to execute
The most important parameter and the only one which is required is the ```tests``` parameter.
Without this parameter ```priceIRC``` will do nothing.

```cmd
--tests="<test_1> <test_2>"
-t="<test_1> <test_2>"
```

As you can see in the box above, the list with tests is separated by whitespace.
A list with all available tests follows.

```
Channel
Connection
MOTD
Ping
PrivateMsg
Robustness
Unknown
WhoIs
```

To execute all tests you can simply use ``All`` as parameter for ``--tests="<tests>"``

An overview of which tests are to execute for which assignment can found [here](https://github.com/punkIRC/punkIRC-test/blob/master/TestOverview.md).

### Examples
**Execute all tests**
```batch
java -jar priceIRC.jar -t="All"
```

**Execute the 'Ping' test for a non-java server**
```batch
java -jar priceIRC.jar -nj -e="./server.sh" -t="Ping"
```

**Execute several tests and override server executable**
```batch
java -jar priceIRC.jar --executable="../../example.jar" -t="Ping Connection Channel"
```

**Execute several tests and override server executable/ hostname and port**
```batch
java -jar priceIRC.jar --executable="../../example.jar" --host="localhorst" -p="7776" -t="Ping Connection Channel"
```

## Logging

At the beginning of each test session ```priceIRC``` generate and display an ID for the session.
In the ```logs``` folder you find an other folder named after the generated ID. Inside this folder
you can find the following files:

##### pricefield
Contains messages and errors from ``priceIRC``.

##### results
Contains a summary of the test results.

##### server_error
Contains all server messages which are 'published' via the error stream. (System.err in java)

##### server_log
Contains all server messages which are 'published' via default output stream. (System.out in java)

## Contributing
If you find bugs, typos or something weird, don't hesitate and create a new issue or create a patch by yourself.
Every help is welcome.

## License
[MIT](https://github.com/punkIRC/punkIRC-test/blob/master/LICENSE)
