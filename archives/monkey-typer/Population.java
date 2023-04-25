public class Population {

    private int generationen;
    private boolean fertig;
    private float mutationsrate;
    private String ziel;
    private DNA[] population;
    private DNA[] partnerpool;
    private String bestes;

    public Population(String pZiel, float pMutationsrate, int pAnzahl) {
        this.population = new DNA[pAnzahl];
        this.mutationsrate = pMutationsrate;
        this.ziel = pZiel;
        this.bestes = "";
        this.generationen = 0;
        this.fertig = false;

        for (int i = 0; i < pAnzahl; i++) {
            this.population[i] = new DNA(pZiel.length());
        }
    }

    public void berechneFit() {
        for (int i = 0; i < this.population.length; i++) {
            this.population[i].berechneFit(this.ziel);
        }
    }

    public void natuerlicheSelektion() {
        int noetigePlaetze = 0;
        for (int i = 0; i < this.population.length; i++) {
            noetigePlaetze += (int) (this.population[i].gibFit() * 100);
        }

        this.partnerpool = new DNA[noetigePlaetze];
        int naechsterPlatz = 0;
        for (int i = 0; i < this.population.length; i++) {
            int plaetze = (int) (this.population[i].gibFit() * 100);
            for (int j = 0; j < plaetze; j++) {
                this.partnerpool[naechsterPlatz] = this.population[i];
                naechsterPlatz++;
            }
        }
    }

    public void neueGeneration() {
        if (partnerpool.length > 1) {

            for (int i = 0; i < this.population.length; i++) {
                int a = (int) (Math.random() * partnerpool.length);
                int b = (int) (Math.random() * partnerpool.length);
                DNA partnerA = partnerpool[a];
                DNA partnerB = partnerpool[b];
                DNA kind = partnerA.crossover(partnerB);
                kind.mutiere(mutationsrate);
                population[i] = kind;
            }
        } else {
            for (int i = 0; i < this.population.length; i++) {
                population[i].mutiere(mutationsrate);
            }
        }

        this.generationen += 1;
    }

    public String gibBestes() {
        return this.bestes;
    }

    public void auswerten() {
        float weltrekord = 0;

        for (int i = 0; i < this.population.length; i++) {
            if (this.population[i].gibFit() > weltrekord) {
                bestes = this.population[i].gibSatz();
                weltrekord = this.population[i].gibFit();
            }
        }

        if (weltrekord == 1) {
            this.fertig = true;
        }
    }

    public boolean istFertig() {
        return this.fertig;
    }

    public int gibGenerationen() {
        return this.generationen;
    }

    public float gibDurchschnittlichenFit() {
        float summe = 0;
        for (int i = 0; i < this.population.length; i++) {
            summe += this.population[i].gibFit();
        }

        return summe / this.population.length; 
    }

    public String gibAlleSaetze(int limit) {
        String alles = "";
        int anzeigeLimit = Math.min(this.population.length, limit);

        for (int i = 0; i < anzeigeLimit; i++) {
            alles += this.population[i].gibSatz() + "\n";
        }

        return alles;
    }
}