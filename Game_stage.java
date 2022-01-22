package sevash.testx;

public class Game_stage
{
	public static final int
		MENU=0,
		WORLD=1,
		NOT_PAUSE=0,
		PAUSE=1,
		EXIT=2;
		
	
	int stage=0;
	int stage_in_world=0;
	public boolean world_load_complete=false;
	
	Game_stage(int stage){
		this.stage=stage;
	}
	
	public void setStage(int stage){
		this.stage=stage;
	}
	
	public int getStage(){
		return stage;
	}
	
	public void setStage_in_world(int stage){
		this.stage_in_world=stage;
	}

	public int getStage_in_world(){
		return stage_in_world;
	}
}
