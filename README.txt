# README.txt
#
# Copyright (C) 2012-2021 Rafael Corchuelo.
#
# In keeping with the traditional purpose of furthering education and research, it is
# the policy of the copyright owner to permit non-commercial use and redistribution of
# this software. It has been tested carefully, but it is not guaranteed for any particular
# purposes.  The copyright owner does not offer any warranties or representations, nor do
# they accept any liabilities with respect to them.

This is the Acme Work Plans, which is intended to be the project that we will work on D02.
Github Repository: https://github.com/angtordom1/Acme-Planner.git
Github Release:

Sobre workplans:
- Debido a que nuestra implementación de spam no permite que ninguna tarea pueda ser creada si se considera spam, 
workplans nunca podrá tener ninguna tarea spam y por consiguiente nunca se podrá considerar spam.

- Hemos decidido dejar workload como atributo dado que el hecho de dejarlo solo como atributo derivado hubiera
resultado en un cálculo de todas las workplans cada vez que se accediera a una lista o un show. En nuestro caso,
al dejarlo como atributo, solo se tendría que hacer dicho cálculo al actualizar o crear una workplan.

- Debido a la última reunión con nuestra tutora, hemos decidido añadir una propiedad a Workplan que será el id del manager
que crea dicha workplan, esto conlleva a que cuando una workplan no tenga tareas, esta pueda seguir viéndose en el listado.
Sin embargo, dicha tarea finalizará al no tener ninguna tarea.

- Además, hay algunas features (como los show en administrator) que en un principio no funcionan como deberían. Entendemos que puede
ser por la falta de datos en la propia Base de Datos (debido a que piden que importemos el initial-data en CleverCloud), y porque cuando 
hemos tenido las reuniones con nuestra tutora, estas funcionalidades iban perfectamente al desplegarlas.

-Tras la sugerencia de nuestra tutora, si una Task es la última de un WokPlan, esta no se podrá eliminar.

- Los casos en los que la cobertura sea menor de 60% o en los que no haya pruebas negativas, se deben a la baja extensión de módulos de la aplicación
y sencillez de los mismos. Por ejemplo, en los listados de shout no hay pruebas negativas ya que no se precisa de ellas.
Nos acogemos a las pautas y seguimientos realizados en tutoría, donde las pruebas fueron revisadas y aprobadas.
Este es el caso de las clase que prueban funcionalidades DELETE y LIST.
En el caso de AdministratorSpamUpdateService alcanzamos un 49% de cobertura, ya que este servicio carece de complejidad alguna y no es posible
cubrir el módulo completo; como se dedujo en tutoría.
