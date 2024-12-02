Compilação:
javac -d bin -cp "jar/jogl-all.jar;jar/gluegen-rt.jar" src/*.java

Execução:
java -Djava.library.path="lib" -cp "bin;jar/jogl-all.jar;jar/gluegen-rt.jar" Principal
