import org.openpatch.scratch.*;
import org.openpatch.scratch.extensions.text.*;

public class MonkeyTyper extends Stage {

    private Text besterSatz;
    private Text alleSaetze;
    private Text statistiken;

    private String ziel;
    private int populationsgroesse;
    private float mutationsrate;

    private Population population;

    public MonkeyTyper() {
        super(800, 600);
        this.besterSatz = new Text();
        this.besterSatz.addFont("comic", "assets/Singkong.ttf");
        this.besterSatz.setPosition(10, 20);
        this.besterSatz.setAlign(TextAlign.LEFT);
        this.besterSatz.setTextSize(20);
        this.besterSatz.switchFont("comic");
        this.besterSatz.setTextColor(200, 50, 50);
        this.add(besterSatz);

        this.alleSaetze = new Text();
        this.alleSaetze.setPosition(400, 10);
        this.alleSaetze.setAlign(TextAlign.LEFT);
        this.add(alleSaetze);

        this.statistiken = new Text();
        this.statistiken.setPosition(10, 200);
        this.statistiken.setAlign(TextAlign.LEFT);
        this.add(statistiken);

        this.ziel = "Die wunderbare Welt der Informatik";
        this.populationsgroesse = 2000;
        this.mutationsrate = 0.01f;

        this.population = new Population(this.ziel, this.mutationsrate, this.populationsgroesse);
    }

    public void run() {   
        if (this.population != null && !this.population.istFertig()) {
            this.population.natuerlicheSelektion();
            this.population.neueGeneration();
            this.population.berechneFit();
            this.population.auswerten();

            String statistikText = "";
            statistikText += "Generationen: " + population.gibGenerationen() + "\n";
            statistikText += "Durchschnittlicher Fit: " + population.gibDurchschnittlichenFit() + "\n";
            statistikText += "Populationsgröße: " + populationsgroesse + "\n";
            statistikText += "Mutationsrate: " + Math.round(mutationsrate*100) + "%\n";

            this.statistiken.showText(statistikText);
            this.besterSatz.showText("Bester Satz:\n" + this.population.gibBestes());
            this.alleSaetze.showText("Alle Sätze:\n" + this.population.gibAlleSaetze(25));
        } 
    }

}