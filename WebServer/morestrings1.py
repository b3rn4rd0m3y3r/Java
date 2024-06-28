#!/usr/bin/python3
print('Content-type: text/html;charset=utf-8 \n\n')
print('<h1>strings PYTHON 3</h1>')

Nome = "simples da silva"

print(f"Muito prazer {Nome.capitalize()} <br>")
print(f"Seu nome tem {Nome.count('a')} letras 'a'.<br>")
print(f"Achei um 'da' na posição {Nome.index('da')}<br>")
print(f"Achei um 'de' na posição {Nome.index('de')}<br>")
