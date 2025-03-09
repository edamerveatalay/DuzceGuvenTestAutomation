#!/bin/bash

# Find all Java files in the project
find_java_files=$(find . -name "*.java")

for file in $find_java_files
do
  # Remove single-line comments (preserving JavaDoc comments)
  sed -i '' -e '/^[[:space:]]*\/\//d' "$file"
  
  # Remove trailing comments
  sed -i '' -e 's/[[:space:]]*\/\/.*$//' "$file"
  
  # Remove multi-line comments (preserving JavaDoc comments)
  # This is complex and potentially risky for a sed one-liner, 
  # so we'll handle just the obvious cases
  sed -i '' -e '/^[[:space:]]*\/\*[^*]/,/\*\//d' "$file"
  
  echo "Processed $file"
done

echo "All Java files processed. Comments removed."
