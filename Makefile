build:
	mvn compile
	#mvn test-compile

assembly:
	mvn compile assembly:single -Dmaven.test.skip=true

#jar:
#	mvn jar:jar

package:
	 mvn clean package -Dmaven.test.skip=true

clean:
	mvn clean

fmt:
	mvn formatter:format

check:
	mvn formatter:validate
#	mvn verify

test:
	mvn test


benchmark:

