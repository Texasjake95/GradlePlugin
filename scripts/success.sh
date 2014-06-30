#!/usr/bin/env bash

# run gradle
./gradlew uploadArchives -Psnapshot=true -PmavenUser=${userName} -PmavenPass=${passWord} -Psigning.keyId=${userID} -Psigning.password=${signPass} -Psigning.secretKeyRingFile=temp.gpg