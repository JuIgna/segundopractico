grammar compiladores;

@header {
package segundopractico;
}

fragment DIGITO: [0-9];
fragment CARACTER: [a-zA-Z];

NUMERO: DIGITO+;
DOUBLE_LITERAL: DIGITO+ '.' DIGITO* | DIGITO* '.' DIGITO+;

INT: 'int';
DOUBLE: 'double';
VOID: 'void';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
RETURN: 'return';
BOOL: 'bool';
TRUE: 'true';
FALSE: 'false';
PRINTF: 'printf';

PYC: ';';
COMA: ',';
IGUAL: '=';
SUMA: '+';
RESTA: '-';
MULT: '*';
DIV: '/';
MOD: '%';
PA: '(';
PC: ')';
LLA: '{';
LLC: '}';
COMP: '==' | '!=' | '<' | '>' | '<=' | '>=';
ID: (CARACTER | '_') (CARACTER | DIGITO | '_')*;
PUNTO: '.';
STRING: '"' ( ~["\\] | '\\' .)* '"';

AND: '&&';
OR: '||';
INCREMENTO: SUMA SUMA;
DECREMENTO: RESTA RESTA;

WS: [ \t\r\n]+ -> skip;

programa: (instruccion)* EOF;

instruccion:
	declaracionFuncion
	| bloque
	| declaracion
	| asignacion
	| estructuraControl
	| llamadaFuncion PYC
	| expresion PYC
	| RETURN expresion? PYC;

bloque: LLA (instruccion)* LLC;

declaracionFuncion: tipo ID PA parametros? PC (bloque | PYC);

parametros: parametro (COMA parametro)*;

parametro: tipo ID;

tipo: INT | DOUBLE | BOOL | VOID;

declaracion: tipo ID (IGUAL expresion)? PYC;

asignacion: ID IGUAL expresion PYC;

estructuraControl: ifElse | whileLoop | forLoop;

ifElse: IF PA expresion PC bloque (elseIf)* (ELSE bloque)?;

elseIf: ELSE IF PA expresion PC bloque;

whileLoop: WHILE PA expresion PC bloque;

forLoop:
	FOR PA (declaracion | asignacion)? (PYC)? expresion? (PYC)? (
		asignacion
		| incrementoDecremento
	) PC bloque;

expresion:
	expresion op_aritmeticos expresion
	| expresion COMP expresion
	| expresion op_logicas expresion
	| PA expresion PC
	| booleano
	| literal
	| ID
	| STRING
	| llamadaPrints
	| llamadaFuncion
	| incrementoDecremento
	| RESTA? literal;

op_aritmeticos: SUMA | RESTA | MULT | DIV | MOD;

literal: NUMERO | DOUBLE_LITERAL;

op_logicas: AND | OR;

llamadaFuncion: ID PA (expresion (COMA expresion)*)? PC;

llamadaPrints:
	PRINTF PA ((STRING (COMA expresion)* | expresion))? PC;

argumentos: expresion (COMA expresion)*;

booleano: TRUE | FALSE;

incrementoDecremento: ID (INCREMENTO | DECREMENTO);