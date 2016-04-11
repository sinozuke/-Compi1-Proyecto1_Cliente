package BackEnd.Analizadores;

import java_cup.runtime.Symbol;
import java.util.*;

%%

%cupsym Simbolo
%class Lexico_reply
%cup
%public
%unicode
%line
%char
%column

%{
%}

numero =[0-9]+ "."? [0-9]*
cadena = [\"] [^\"]* [\"] 
ComentarioLinea = "//"([^\n\t])*(\t|\n) 
ComentarioMultilinea= "#*" ([^*#]|[*])* "*#"
%%

"$"                     {return new Symbol(Simbolo.DI, yycolumn,yyline,yytext()); }
"-$"			{return new Symbol(Simbolo.DF, yycolumn,yyline,yytext()); }
"reply"           	{return new Symbol(Simbolo.Reply, yycolumn,yyline,yytext()); }
"Usuario"           	{return new Symbol(Simbolo.usuario, yycolumn,yyline,yytext()); }
"id"             	{return new Symbol(Simbolo.Id, yycolumn,yyline, yytext()); }
"access"           	{return new Symbol(Simbolo.Access, yycolumn,yyline,yytext()); }
"registro"              {return new Symbol(Simbolo.Registro, yycolumn,yyline,yytext()); }
"tienda"          	{return new Symbol(Simbolo.Tienda, yycolumn,yyline,yytext()); }
"modificar"          	{return new Symbol(Simbolo.Modificar, yycolumn,yyline,yytext()); }
"eliminar"              {return new Symbol(Simbolo.Eliminacion, yycolumn,yyline,yytext()); }
"sucursal"              {return new Symbol(Simbolo.Sucursal, yycolumn,yyline,yytext()); }
"producto"              {return new Symbol(Simbolo.Producto, yycolumn,yyline,yytext()); }
"error"                 {return new Symbol(Simbolo.ERR,yycolumn,yyline,yytext());}
"tipo"                  {return new Symbol(Simbolo.TIPO,yycolumn,yyline,yytext());}
"posicion"              {return new Symbol(Simbolo.PO,yycolumn,yyline,yytext());}
"fila"                  {return new Symbol(Simbolo.FILA,yycolumn,yyline,yytext());}
"columna"               {return new Symbol(Simbolo.COLUMNA,yycolumn,yyline,yytext());}
"descripcion"           {return new Symbol(Simbolo.DESC,yycolumn,yyline,yytext());}
"lista"         	{return new Symbol(Simbolo.Lista, yycolumn,yyline,yytext()); }
"dirección"      	{return new Symbol(Simbolo.Direccion, yycolumn,yyline,yytext()); }
"nombre"                {return new Symbol(Simbolo.Nombre, yycolumn,yyline,yytext()); }
"telefono"      	{return new Symbol(Simbolo.Telefono, yycolumn,yyline,yytext()); }
"código"                {return new Symbol(Simbolo.Codigo, yycolumn,yyline,yytext()); }
"propietario"      	{return new Symbol(Simbolo.Propietario, yycolumn,yyline,yytext()); }
"cantidad"              {return new Symbol(Simbolo.Cantidad, yycolumn,yyline,yytext()); }
"marca"                 {return new Symbol(Simbolo.Marca, yycolumn,yyline,yytext()); }
"color"                 {return new Symbol(Simbolo.Color, yycolumn,yyline,yytext()); }
"tamaño"                {return new Symbol(Simbolo.Tamano, yycolumn,yyline,yytext()); }

"="                     {return new Symbol(Simbolo.Igual, yycolumn,yyline,yytext()); }
{cadena}		{	String str=yytext();
                     		str = str.substring(1,str.length()-1);
 				System.out.println(str);
				return new Symbol(Simbolo.cadena, yycolumn,yyline,str);
			}

"True"                  {return new Symbol(Simbolo.bool, yycolumn,yyline,yytext()); }
"Falso"                 {return new Symbol(Simbolo.bool, yycolumn,yyline,yytext()); }
"Fail"                  {return new Symbol(Simbolo.bool, yycolumn,yyline,yytext()); }

{numero}                {return new Symbol(Simbolo.numero, yycolumn,yyline,yytext()); }

{ComentarioLinea}       {}
{ComentarioMultilinea}  {}
[ \t\r\f\n]+		{}
.			{
    System.out.println("cagada con: " + yytext() + " " + yyline + " " + yycolumn);
    for(int i=0;i<yytext().length();i++){
        System.out.println(yytext().charAt(i) + " = " + yytext().codePointAt(i));
    }
}