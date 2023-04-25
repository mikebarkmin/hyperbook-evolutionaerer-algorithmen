public class DNA {
    private char[] gene;
    private float fit;
    
    public DNA(int anzahlGene) {
        gene = new char[anzahlGene];
        for (int i = 0; i < gene.length; i++) {
            gene[i] = this.zufallsbuchstabe();
        }
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
    
    public float gibFit() {
        return fit;
    }
    
    public String gibSatz() {
        return new String(gene);
    }
    
    public DNA crossover(DNA partner) {
        DNA kind = new DNA(gene.length);
        
        int cutoff = (int) (Math.random() * gene.length);
        
        for (int i = 0; i < gene.length; i++) {
            if (i > cutoff) {
                kind.gene[i] = this.gene[i];
            } else {
                kind.gene[i] = partner.gene[i];
            }
        }
        
        return kind;
    }
    
    public void mutiere(float mutationsrate) {
        for (int i = 0; i < gene.length; i++) {
            if (Math.random() < mutationsrate) {
                gene[i] = this.zufallsbuchstabe();
            }
        }
    }
    
    private char zufallsbuchstabe() {
        // siehe https://www.ascii-code.com/
        char zeichen = (char) (Math.random() * (127 - 32) + 32);
        return zeichen;
    }
}