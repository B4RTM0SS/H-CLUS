@echo off

call javac -d ./Client/out/ -sourcepath ./Client/src/ ./Client/src/MainTest.java > nul 2>&1

if errorlevel 1 (
	echo Compilation error: %errorlevel%
    pause
    exit /b 1
)

call jar cef MainTest ./Client/jar/Client.jar -C ./Client/out/ ./ > nul 2>&1

if errorlevel 1 (
    echo JAR creation error: %errorlevel%
    pause
    exit /b 2
)

call javadoc -d ./Client/javadoc/ -windowtitle "Laboratorio MAP - Client" -sourcepath ./Client/src/ ./Client/src/*.java > nul 2>&1

if errorlevel 1 (
    echo JavaDoc creation error: %errorlevel%
    pause
    exit /b 3
)

set /p serverIP=Enter server IP: 
set /p serverPort=Enter server Port: 

echo.

java -jar ./Client/jar/Client.jar %serverIP% %serverPort%

pause > nul