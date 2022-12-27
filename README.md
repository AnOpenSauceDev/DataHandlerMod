# dataHandlingLib
 an incredibly basic and fast data saving and writing library
 threw this together so i use it in my projects.
 
 # how to use
 
there are two things you really need to know:
1. `ThreadedDataReader`
2. `ThreadedDataWriter`

## Writing Data
```java
public void WriteData(){
Object data = "an object can be anything!";
new ThreadedDataWriter().Write("ModID","VariableID",data);
}
```
## Reading Data
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
as of the time this is being written, client and server data will not be synced.
This is due to the fact that ~~there is no real way to handle the syncing of large files in a reasonable matter.~~
im lazy.
