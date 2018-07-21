# inkubator
#### spawning cool new kotlin projects
<br>

# prism
#### brute force estimation of time complexity of any algorithm
<br>

## run it
1. clone the project
2. open it in your favorite IDE
3. run *PrismApplication.kt*

<br>
<br>

# kit
#### showcasing kotlin scripting capabilities
<br>

## scripts
Because kotlin scripts are currenty lacking on the dependency management aspect the easiest way to run one of the scripts in *kit* is to open and build the project in intellij and run them as described bellow:

| script | description | additional arguments |
|--- | --- | --- |
| commandexecuter.kts | executes any command | ```COMMAND```<sup>1</sup> |
| xcommandexecuter.kts | executed any command using kotlin coroutines<sup>2</sup> | ```COMMAND -Xcoroutines=enable```<sup>1</sup>|

<sup>1</sup> replace ```COMMAND``` with a valid *shell command*, eg. ```"ping google.com"``` <br>
<sup>2</sup> used to read from standard and error output streams in a parallel fashion; more on coroutines [here](https://kotlinlang.org/docs/reference/coroutines.html) <br>


