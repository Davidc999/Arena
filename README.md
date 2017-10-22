# Arena
Arena

Some stuff I had problems with, when trying to get this to work:

1.
  error: java invalid source release 8
  Need to change to java 8 (1.8) In the following places
  * File -> Project Structure -> Project Settings
  * File -> Project Structure -> Module Settings -> Tab: Sources: Language Level
  * File -> Project Structure -> Module Settings -> Tab: Dependencies: Module SDK
  * File -> Settings -> Compiler -> Java Compiler -> Target bytecode version
  sources + screenshots: https://stackoverflow.com/questions/25878045/errorjava-invalid-source-release-8-in-intellij-what-does-it-mean
  
2.
  Null exception when trying to load the spritesheet. 
  class.getResource() is actually the method returning null. 
  This is because it looks for the file inside a path relative to the RESOURCE ROOT.
  To set this:
  Right-click the res folder
  'Mark directory as' -> Resources root.
  
This is all for troubleshooting so far :)
