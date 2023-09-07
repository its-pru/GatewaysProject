# Project 1: Gateways

## Information: 

Worked with group to create layered system that imitates enterprise architecture. We created the database layer, model layer, and left out the presentation layer.

- First we created 3 databases of different styles. Single table inheritance, concrete table inheritance, and class table inheritance.
- Had to deal with different relationsips because of these different styles (1-1, 1-n, n-1, n-n).
- Built row data and table data gateways in database layer so we could gather the data from one row or multiple rows depending on that is needed (All SQL lives here).
- Built row data and table data gateways for each style of database.
- Built mappers to deal with data coming from gateways and data transfer objects so the data always looks the same in the model layer.
- Built controllers at the top of the domain layer that would deeal with common requests coming from the presentation layer.
- Built tests for both layers along the way.

Group Members:
- Evan Paules
- Luke Henry
- Taylor Fernandez
- Tommy Prusak
- Andrew Boram
- Jacob Karpovich
- Austin Pliska
