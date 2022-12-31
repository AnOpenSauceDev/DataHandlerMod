# <img src="https://github.com/AnOpenSauceDev/DataHandlerMod/blob/main/cringe_joke.gif?raw=true" width="40" height="40" /> dataHandlingLib

 an incredibly basic and fast data saving and writing library.
 I threw this together so i use it in my projects.
 
 (it's kinda public because im too lazy to setup my tokens and dont wan't to deal with trying to find some old folder.)
 
## 🤔 how to use this

## 🔧 setup

in your `build.grade` file,

```gradle

repositories {
    flatDir {
        dirs 'YOUR_LIBRARY_FOLDER_NAME'
    }
}

dependencies {
    modImplementation 'me.wolfie.dataentry:1.0' // this will look for a file called "1.0", so you might want to change that.
}
```

## 💻 writing code for this
 
there are two things you really need to know when using this _~~cursed abomination~~_ mod:
1. `ThreadedDataReader`
2. `ThreadedDataWriter`

## ✏ Writing Data
```java
public void WriteData(){
Object data = "an object can be anything!";
new ThreadedDataWriter().Write("ModID","VariableID",data);
}
```
## 📖 Reading Data
```java
public void ReadData(){
Object data;
ThreadedDataReader reader = new ThreadedDataReader();
 reader.Read("ModID","VariableID");
 data = reader.output;
}
```

# is this for you?
**possibly**, as long as you don't need advanced and complicated file structures.
as of the time this is being written, **client and server data will not be synced.**
This is due to the fact _~~that there is no real way to handle the syncing of large files in a reasonable matter.~~_
that im lazy.

### side notes
expect to see this in my current (possibly) and future mods.
standard MIT license stuff applies here too and so on.
