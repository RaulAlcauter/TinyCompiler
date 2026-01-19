package alex;

import errors.GestionErroresTiny;

%%
%cup
%class AnalizadorLexicoTiny
%public
%unicode
%line
%column

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;

  public String lexema() { return yytext(); }
  public int fila() { return yyline + 1; }
  public int columna() { return yycolumn + 1; }

  public void fijaGestionErrores(GestionErroresTiny errores) {
    this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}



letra      = [a-zA-Z]
ident      = {letra}({letra})*
blanco     = [ \t\r\n]+
comentario = "#"[^#]*"#"

si        = si
sino      = sino
mientras  = mientras
ent       = ent
bool      = bool
verdadero = verdadero
falso     = falso
porfavor  = porfavor

cero       = cero
uno        = uno
dos        = dos
tres       = tres
cuatro     = cuatro
cinco      = cinco
seis       = seis
siete      = siete
ocho       = ocho
nueve      = nueve

mas        = \+
menos      = \-
mul        = \*
igual      = \=
igualigual = \=\=
mayor      = \>
menor      = \<

pcoma = \;
pa = \(
pc = \)
ca = \[
cc = \]
la = \{
lc = \}

%%

{blanco}          { }
{comentario}      { }

/* Palabras clave */
{si}              { return ops.unidadIf(); }
{sino}            { return ops.unidadElse(); }
{mientras}        { return ops.unidadWhile(); }
{ent}             { return ops.unidadTipoEnt(); }
{bool}            { return ops.unidadTipoBool(); }
{verdadero}       { return ops.unidadTrue(); }
{falso}           { return ops.unidadFalse(); }
{porfavor}        { return ops.unidadPorFavor(); }

/* Numeros */
{cero}            { return ops.unidadEnt("0"); }
{uno}             { return ops.unidadEnt("1"); }
{dos}             { return ops.unidadEnt("2"); }
{tres}            { return ops.unidadEnt("3"); }
{cuatro}          { return ops.unidadEnt("4"); }
{cinco}           { return ops.unidadEnt("5"); }
{seis}            { return ops.unidadEnt("6"); }
{siete}           { return ops.unidadEnt("7"); }
{ocho}            { return ops.unidadEnt("8"); }
{nueve}           { return ops.unidadEnt("9"); }

/* Identificadores */
{ident}           { return ops.unidadId(); }

/* Operadores */
{mas}             { return ops.unidadSuma(); }
{menos}           { return ops.unidadResta(); }
{mul}             { return ops.unidadMul(); }
{igualigual}      { return ops.unidadIgualIgual(); }
{igual}           { return ops.unidadIgual(); }
{mayor}           { return ops.unidadMayor(); }
{menor}           { return ops.unidadMenor(); }

/* Simbolos */
{pcoma}           { return ops.unidadPuntoComa(); }
{pa}              { return ops.unidadPA(); }
{pc}              { return ops.unidadPC(); }
{ca}              { return ops.unidadCA(); }
{cc}              { return ops.unidadCC(); }
{la}              { return ops.unidadLA(); }
{lc}              { return ops.unidadLC(); }

/* ERRORES */
[0-9]+             { errores.errorLexico(fila(), columna(), lexema()); }
.                  { errores.errorLexico(fila(), columna(), lexema()); }
