#!/usr/bin/env bash

curl -v -X POST http://localhost:3449/api/string/is_scramble \
     -H 'Cache-Control: no-cache' \
     -H 'Content-Type: application/json' \
     -d '{"s1": "1", "s2": "1"}'
