@echo off

setlocal EnableDelayedExpansion

set /P user=Enter root database user:
set /P password=Enter user %user% password:
echo | set /p=Connecting to database...
mysqlsh -u %user% --password=%password% --sql --file=script.sql > NUL 2>&1

if errorlevel 1 (

	echo connection failed.

) else (

	echo connection successful^^!

	set MYSQL_JAR=Server\dependencies\mysql-connector-java-8.0.17.jar
	
	set /P PORT=Enter server port: 

	echo | set /p=Compiling...
	javac -sourcepath Server\src -d Server\out Server\src\MainTest.java > NUL 2>&1

	if errorlevel 1 (

		echo compilation failed.

	) else (

		echo compilation successful^^!

		echo | set /p=Creating JAR...
		jar -cfe Server\jar\server.jar MainTest -C Server\out .

		if errorlevel 1 (
			
			echo JAR creation failed.

		) else (

			echo JAR created successfully^^!

			echo | set /p=Generating Javadoc...
			javadoc -d Server/javadoc/ -windowtitle "Laboratorio MAP - Server" -sourcepath Server/src/ Server/src/*.java > NUL 2>&1

			if errorlevel 1 (

				echo Javadoc generation failed.

			) else (

				echo Javadoc generated^^!

				echo | set /p=Starting server...
				java -cp !MYSQL_JAR!;Server\jar\server.jar MainTest !PORT!

				if errorlevel 1 (

					echo Server failed.

				)

			)

		)

	)

)

Pause > NUL
