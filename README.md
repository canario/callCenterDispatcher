# callCenterDispatcher

Este proyecto se encarga de despachar llamadas de un call center a los
operadores, a los supervisores en caso de no haber operadores disponibles y
a los directores en caso de no haber supervisores disponibles.

# Mejoras

## Solución para cuando no hay empleados disponibles

Se crea una cola de llamadas y se van tomando las llamadas desde esa cola cuando
hay empleados disponibles.

## Solución al problema de mas de 10 llamadas concurrentes

Se puede crear un pool dinamico para poder tener mas threads para atender las
llamadas. Otra mejora seria que la cantidad de threads con los que se
inicializa la clase se tome de system properties o de un archivo de propiedades.


## Mejora a la lista de empleados

En lugar de usar listas de empleados se podrian usar colas de empleados, asi se
asegura que el empleado que se asigna es el que lleva mas tiempo sin atender una
llamada. Las colas también deberian se sincronicas.
