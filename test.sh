#!/usr/bin/env bash

TOKEN='eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJNUC04TTIzUUtLTDRXNTcyWkJETUJOUDNLSE5WTTZRU1M0RiIsImlhdCI6MTU2NDMyNTQxMiwiZXhwIjoxNTY0MzI5MDEyfQ.VsC6-19LiUjrhq6d1zStWkO0f1qXVnBdDARna73Kp9Noa97v58ktXHVZJJ91wxUragv1jML7khpV9hw0c779gORMZEg5auld2p9T0riCfQNEs9sBScK8hfer3Uz6fu9HRLDR2rVb-GxXZ3Ub4H4bj5Viekjr3qMKkGrheKGLG5dBRq04mIqTvAj62vT1hhBfqJGHsbQqz70UTBa0YabQseI4zCRHpSFT-a_XiKyqC0X4T_ps0yMlym3JQpI38pRK6OYrqUwrh5sDIOoI3bA6XRpFUBQ5cB-K8TtTx-RB1H6j-rnUY5KFKHhN93_isdK3frn5G0I4dBKszYVJ9cbeQw'

curl -v -X POST http://localhost:3000/api/string/is_scramble \
     -H 'Cache-Control: no-cache' \
     -H 'Content-Type: application/json' \
     -d '{"s1": "werewrew", "s2": "wewew"}'
