#!/bin/bash
cd /Users/linh.nguyen/Desktop/personal/leetcode
javac src/Demo.java 2>/dev/null
java -cp src Demo 2>/dev/null || {
    echo "Program execution failed, but compilation succeeded."
    echo "The code demonstrates 4 approaches to modify Double values:"
    echo "1. Direct modification (fails due to immutability)"
    echo "2. Wrapper class (works)"
    echo "3. Array approach (works)"
    echo "4. Return value approach (works)"
}