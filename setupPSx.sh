#!/bin/env bash

max=-1
for d in ps*/; do
  [[ -d "$d" ]] || continue
  num="${d#ps}"   # Remove 'ps' prefix
  num="${num%%/}" # Remove trailing slash
  if [[ "$num" =~ ^[0-9]{2}$ ]]; then
    num10=$((10#$num)) # Interpret as base 10
    if ((num10 > max)); then
      max=$num10
    fi
  fi
done

next=$(printf "%02d" $((max + 1)))
if ((next > 99)); then
  echo "Error: Cannot create ps$next, limit is ps99."
  exit 1
fi

echo "ps$next"
# mkdir "ps$next"
mkdir "ps$next"

for arg in "$@"; do
  dir="./ps$next/$arg"
  mkdir "$dir"
  cp -ra ./template/. "$dir"
done
