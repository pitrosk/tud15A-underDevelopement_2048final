package ned.tud15a.underDevelopment;

public class ScoreObserver extends Observer {
	int score=  0;
	
	public ScoreObserver (Subject subject){
	      this.subject = subject;
	      this.subject.attach(this);
	   }
	@Override
	public void update() {
		// TODO Auto-generated method stub
		score += subject.getState();
        System.out.println("Score : "  + score);
	}

}
