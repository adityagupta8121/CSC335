
import java.util.List;

public class ConcreteDocument extends Document{
	
	@Override
	public void draw(List<Row> rows){		
		this.setRows(rows);
	}
		
	
}
