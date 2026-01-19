#!/bin/bash

set -e

echo ". Generando analizador léxico (JFlex)"
java -jar jflex.jar mini_lexico.l

if [ -f AnalizadorLexicoTiny.java ]; then
  mv AnalizadorLexicoTiny.java alex/
fi

echo ". Generando analizador sintáctico (CUP)"
java -cp cup.jar java_cup.Main \
  -parser ConstructorAST \
  -symbols ClaseLexica \
  -nopositions \
  Tiny.cup

mv ConstructorAST.java constructorast/
mv ClaseLexica.java constructorast/

echo ". Compilando todo el proyecto"
javac -cp cup.jar \
  alex/*.java \
  ast/*.java \
  errors/*.java \
  constructorast/*.java

echo ". Ejecutando compilador con input_ok.txt "
java -cp cup.jar:. constructorast.Main input_ok.txt

echo " TODO OK "
