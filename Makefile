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

fmt_proto: ## go fmt protobuf file
	find . -name '*.proto' -not -path "./vendor/*" | xargs clang-format -i

proto:build

check:
	mvn formatter:validate
#	mvn verify

test:
	mvn test


benchmark:

