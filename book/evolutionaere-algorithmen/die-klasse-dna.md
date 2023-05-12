---
name: Die Klasse DNA
lang: de
index: 0
---

# Die Klasse DNA

Die Desoxyribonukleinsäure, meist kurz als DNA bezeichnet, trägt Erbinformation bei allen Lebewesen und den DNA-Viren. Die DNA besteht aus verschiedenen Abschnitten - den Genen. Ein Gen stellt die Grundinformationen für die Entwicklung von Eigenschaften eines Individuums zu bereit. Beim sogenannten Crossover werden Gene ausgetauscht. Bei der Mutation werden Gene verändert.

## Das Klassendiagramm

Für die evolutionären Algorithmen benötigen wird die folgende Klasse.


```mermaid
classDiagram
    class DNA {
        -gene: T[]
        -fit: float
        +DNA(anzahlGene: int)
        +berechneFit(ziel: T)
        +gibFit(): float
        +crossover(partner: DNA): DNA
        +mutiere(rate: float)
    }
```

:::alert{info}
Die eckigen Klammern [] repräsentieren unsere erste lineare Datenstruktur das Array. Ein Array ist nicht anderes als eine Sammlung von gleichartigen Daten mit festgelegter Größe. Im Konzeptspeicher findest du mehr Informationen zum Thema [Arrays](http://www.barkmin.eu/hyperbook-konzeptspeicher/programmierung/datenstrukturen/array).
:::

Das ist der Grundaufbau für unsere zwei Anwendungsfälle. Das T bezeichnet einen beliebigen Datentyp für die Gene. Dieser Datentyp T kann ein Integer, ein String oder eine eigene Klasse sein. Diesen müssen wir für jeden Anwendungsfall neu wählen.

### Aufgabe

Implementiere das Gerüst der Klasse DNA, das heißt, dass nur die Methodenköpfe implementiert werden müssen und keine Funktionalität. Verwende für T den Datentyp char.

:::onlineide

```java DNA.java
public class DNA {

}
```

:::

## Eine Hilfsmehtode: Zufallsbuchstabe

Im UML-Klassendiagramm hat eine kleine Hilfsmethode gefehlt, die wir für die folgenden Aufgaben benötigen.

Die Methode `zufallsbuchstabe` soll einen char-Wert zwischen 127 und 32 erzeugen.

:::alert{info}
char-Werte stehen in Beziehung zu der ASCII-Tabelle. Die ASCII-Werte zwischen 32 und 127 sind alle druckbaren Zeichen. (Siehe: https://www.ascii-code.com)
:::

### Aufgabe

Implementiere die Methode `private char zufallsbuchstabe()`, sodass sie einen char-Wert zwischen 32 und 127 zurückgibt. Nutze dazu die Methode `Math.random()`. `Math.random()` gibt einen Double-Wert zwischen 0 und 1 zurück.

:::collapsible{title="Hilfe" id="590546"}

Der folgende Quelltext könnte dir helfen. Was tut er bloß?

`(char) (Math.random() * (max - min) + max)`

Teste den Quelltext mit verschiedenen max, min und Math.random() Werten. Zum Beispiel min = 3, max = 10, Math.random() = 0.2

:::

## Der Konstruktor

Im Konstruktor wollen wir die Attribute gene und fit initial belegen.

Ein Array initalisiert man mit einer speziellen Schreibweise: `int[] test = new int[10];`

Dabei ist `int[]` der Datentyp "Array von int-Werten". Der Ausdruck `new int[10]` reserviert einen Speicherbereich, in dem 10 int-Werte Platz haben und liefert eine Referenz darauf zurück. Nach der Zuweisung (=) zeigt die Variable test auf diesen Speicherbereich.

Die zehn Werte nennt man **Elemente**. Sie werden **mit den Indizes 0 bis 9** durchnummeriert.

Du kannst mit `test[3]` auf ein Element zugreifen und mit `test[2] = 29` einem Element einen neuen Wert zuweisen.

### Aufgabe

1. Verändere den Konstruktor so, dass der Parameter `anzahlGene` für die Anzahl der Plätze des Arrays `gene` genutzt wird und das Zufallsbuchstaben gesetzt werden.

:::onlineide

```java DNA.java

DNA meineDNA = new DNA(5);
System.out.println(meineDNA);

public class DNA {

  private char[] gene;

  public DNA(int anzahlGene) {
    gene = new int[10];
    for (int i = 0; i < gene.length; i++) {
      gene[i] = 'a';
    }
  }

  public char zufallsbuchstabe() {
    return (char) (Math.round(Math.random() * (127 - 32) + 32));
  }

  public String toString() {
    return String.valueOf(this.gene);
  }
}

```
:::

2. Teste deine Implementierung, indem du den grünen Play-Button drückst.

## Die Methode: Crossover

Die Methode Crossover soll das aktuelle DNA-Objekt und ein weiteres nehmen und ein neues Erstellen. Dabei eine Zufallszahl angeben wie viel von der ersten DNA und wie viel von der zweiten DNA genommen wird.

Der folgende Pseudocode zeigen den Algorithmus:

::struktog{data="https://struktog.openpatch.org/#pako:eNqVlN1q3UYQx19F6DaesN-za9pCmoYSCLkK9CL0Yj9mjoRlHaOjgxsHv00eI3d-sY5NEuPCcSNdiN3Ranb___nNfu4v942m_vxzP7b-vCdlcnXagzK1gfJaQ3Q2Q4zeO7bUn_XrpyuSlW_nAy3re_lbYryfpv31m4kuaV5_JMucguXgwcSI0ErKUNgjWKXRtxoek33Ih4tvqVb6RzL0b5YbOu6oo3GmbqYjdX-8f9Ud5yaTWUKHkbqLcW4nNw8cbfDogLgxcM4edHLyClU3q9smJQ5bUck00IY8RGoZOGkFRDar5vlnlczdzZHvvkzTuJPxh0WkXMve3c31eKiDhNSDxEZL92q-ycP0MPyT5tNH8zZobjpB1KzBoTidSyIIKhj0BjfpxKRCLVSglBwg6xYhNZ2hVdccBfuY7PX-OK_v9vurp2L57uvSjd2v34SM3S-dKKWXE827degux7UbX7x4Row1zgl0wSkLiTMCmuggB0JjQ94kplCU0nMBi6WCy5HAekYwSWkVbXmuaLuxPOAl9VruvtaL04xj0DHHAoEMQTGOoTjrgLHG1vAnMZuP03R7e3vW12Gc2o_kpmTUFq20n7Sk9FKEkFkBhlIr-W3daJRKwReE5H0CY4wHSgKKo0j4xNvflzzX4akhfwmqUs7fHqE9uVHKmY3FCljl6EbZAs3WBEyBVQ28wRJZuRzp9RNTaspy3krALlmoKIxUHRiaNrVg1ZtMSa6xM3InlewcpBKSsKYyEFKVS4KeY-Sej5f3dH8c_xbi12E8fJ-e3E8TMxrBMTdyUG3WkEzSIFEbMG7FhfN0-I893lUlJw_3GDKE0uQGTyqB18yeMG5rIaNQ6WrBkwrgVXCAMRswXkWNmTfYc5WXdablfx2yVYv5pYEt_p4eZ0HXlCD4EAI6vcmh789ZP9C4G-SDsfGsvx7bOvTnSfvbfwFM4Cuf"}

### Aufgabe

1. Implementiere die Methode crossover.

:::onlineide

```java DNA.java

DNA meineDNA = new DNA(5);
System.out.println(meineDNA);

DNA partnerDNA = new DNA(5);
System.out.println(partnerDNA);

DNA neueDNA = meineDNA.crossover(partnerDNA);
System.out.println(neueDNA);

public class DNA {

  private char[] gene;

  public DNA(int anzahlGene) {
    gene = new char[anzahlGene];
    for (int i = 0; i < gene.length; i++) {
      gene[i] = this.zufallsbuchstabe();
    }
  }

  public char zufallsbuchstabe() {
    return (char) (Math.round(Math.random() * (127 - 32) + 32));
  }

  public DNA crossover(DNA partner) {

  }

  public String toString() {
    return String.valueOf(this.gene);
  }
}

```
:::

2. Teste deine Implementierung, indem du den grünen Play-Button drückst.

## Die Methode: Mutiere

Die Methode Mutiere soll alle Gene durchlaufen und abhängig vom Parameter
rate ein Gen durch einen Zufallsbuchstaben ersetzten.

### Aufgabe

1. Vervollständige die folgende Implementierung und überführe die Methode in dein BlueJ-Projekt.

:::onlineide

```java DNA.java

DNA meineDNA = new DNA(5);
System.out.println(meineDNA);
meineDNA.mutiere(0.7);
System.out.println(meineDNA);

public class DNA {

  private char[] gene;

  public DNA(int anzahlGene) {
    gene = new char[anzahlGene];
    for (int i = 0; i < gene.length; i++) {
      gene[i] = this.zufallsbuchstabe();
    }
  }

  public char zufallsbuchstabe() {
    return (char) (Math.round(Math.random() * (127 - 32) + 32));
  }

  public void mutiere(float rate) {
    for (int ; i <; i) {
      if (Math.random() < rate) {
        gene = ;
      }
    }
  }

  public String toString() {
    return String.valueOf(this.gene);
  }
}

```
:::

## Die Methode: Berechne Fit

Die Methode berechneFit soll ausrechnen wie nah man mit der DNA an einem gewünschten Ergebnis dran ist. 

In unserem Beispiel wollen wir schauen wie nah wir an einem gewünschten Satz sind. 

"Alge"

### Aufgabe

In der nachvollgenenen Implementierung ist die berechneFit-Methode schon implementiert. Deine Aufgabe ist es die Methode zu testen.

1. Schreibe im Berech TESTEN Quelltext zum Testen der Methode, indem du die vollgenden Schritte ausführst.
  1. Erzeuge einen String zielWort und belege ihn mit dem Zielwort.
  2. Erzeuge ein DNA-Objekt, welche soviele Gene hat wie das Zielwort Buchstaben.
  3. Rufe die Methode berechneFit mit dem Zielwort auf dem DNA-Objekt auf.
  4. Gib den berechneten Fitwert aus.
2. Erzeuge 1000 DNAs und testen den Fit bezüglich des Zielwortes.

:::onlineide

```java DNA.java

// TESTEN

// TESTEN

public class DNA {

  private char[] gene;
  private float fit;

  public DNA(int anzahlGene) {
    gene = new char[anzahlGene];
    for (int i = 0; i < gene.length; i++) {
      gene[i] = this.zufallsbuchstabe();
    }
  }

  public float gibFit() {
    return fit;
  }

  public char zufallsbuchstabe() {
    return (char) (Math.round(Math.random() * (127 - 32) + 32));
  }

  public void berechneFit(String ziel) {
    int score = 0;
    for (int i = 0; i < gene.length; i++) {
      if (gene[i] == ziel.charAt(i)) {
        score++;
      }
    }
    fit = (float) score / (float) ziel.length();
  }

  public String toString() {
    return String.valueOf(this.gene);
  }
}
```

:::