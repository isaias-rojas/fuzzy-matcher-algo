<a name="br1"></a> 

## Fuzzy Matching

**Integrantes:**

* Mayerli Santander Sejas
* Gabriela Trujillo Lozada
* Isaias Rojas Condarco

1. Use the pre-processing and tokenization that already exists in the library and make
sure to implement new ones. (Use at least two that already exist and two new ones.)

| Element Type     | PreProcessing Function              | Tokenizer Function     |
|------------------|-------------------------------------|------------------------|
|                  | ________Que usen existentes________ |                        |
| ___AGE___        | numberPreprocessing()               | valueTokenizer()       |
| ___MODEL___      | removeSpecialChars()                | wordTokenizer()        |
| ___OCCUPATION___ | occupationNormalization()           | wordTokenizer()        |
|                  | que usa los existentes:             |                        |
|                  | removeSpecialChars()                |                        |
|                  | toLowerCase()                       |                        |
|                  | ________Que usen nuevos________     |                        |
| __RELEASE\_DATE__| releaseDateNormalization()          | releaseDateTokenizer() |
| ___GENDER___     | genderNormalization()               |                        |

2. Implement one of the matching algorithms you mentioned in the previous task.
(Compare the algorithm implemented with the one already in the library.)

    a. Compare score and time complexity.

El algoritmo implementado en la biblioteca se basa en la comparación directa de tokens y
elementos, así como en la creación de un índice de búsqueda para la búsqueda eficiente de
coincidencias. Este enfoque puede considerarse como un algoritmo de búsqueda y
coincidencia basado en tokens y reglas específicas.

Por otro lado, el algoritmo de Levenshtein implementado es una forma de calcular la
distancia de edición entre dos cadenas de texto. La distancia de Levenshtein mide el
número mínimo de operaciones necesarias para transformar una cadena en otra, donde las
operaciones pueden ser inserciones, eliminaciones o sustituciones de caracteres.

Ahora, comparando estos dos enfoques:

**Algoritmo de la biblioteca (basado en tokens y reglas específicas):**

- **Puntaje de similitud:** Este algoritmo puede proporcionar puntajes de similitud
basados en reglas específicas definidas por el usuario. El puntaje reflejará cuán bien
coinciden los elementos según estas reglas.

<a name="br2"></a> 

- **Complejidad temporal:** La complejidad temporal de este algoritmo puede variar
según la implementación y las reglas específicas utilizadas. En general, puede ser
eficiente para conjuntos de datos pequeños a medianos, pero podría ser menos
eficiente para conjuntos de datos grandes o reglas complejas.

**Algoritmo de Levenshtein:**

- **Puntaje de similitud:** Este algoritmo proporciona un puntaje de similitud numérico que
representa la distancia entre dos cadenas. Cuanto menor sea la distancia, más
similar serán las cadenas.

- **Complejidad temporal:** La complejidad temporal del algoritmo de Levenshtein es de
O(m \* n), donde m y n son las longitudes de las cadenas de texto que se están
comparando. A medida que aumenta el tamaño de las cadenas, el tiempo de
ejecución también aumenta linealmente.

En resumen, el algoritmo de la biblioteca se centra en la búsqueda y coincidencia eficientes
de elementos basados en tokens y reglas específicas, mientras que el algoritmo de
Levenshtein proporciona una medida objetiva de la similitud entre cadenas de texto basada
en la distancia de edición. La elección entre estos dos enfoques dependerá de las
necesidades específicas del proyecto y del tipo de datos que se estén comparando.

