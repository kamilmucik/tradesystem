rem mvn install:install-file -DgroupId=com.sun.winsw -DartifactId=winsw -Dversion=1.18 -Dclassifier=bin -Dpackaging=exe -Dfile=lib/winsw-1.18-bin.exe


mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=lib/ojdbc6-11.2.0.4.jar -DgeneratePom=true

