#!/bin/bash

# Base URL of the server
BASE_URL="http://172.20.10.2:8080/"

# Download the PDF file
wget "$BASE_URL/CS61C Cirriculum.pdf"

# Download each discussion folder
for i in {01..14}; do
    wget -r -np -nH --cut-dirs=1 "$BASE_URL/Discussion%20$i/"
done

echo "Download complete."

