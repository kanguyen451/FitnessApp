import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Write code to load the workouts from the provided workouts.csv file. The function should return a Workouts object.

public class FileAccess {
  
  public static Workouts loadWorkouts() {
    // What is a try/catch block and why do we need one?
	  //in case there is an error that is predictable,
	  //we can insert a series of steps that the program takes in order to handle the error
    // What is an exception?
	  //an exception is a situation the program recognizes as an error and will act accordingly depending on
	  //what the situation is
	  Workouts w1 = new Workouts();
	  try (Scanner scanner = new Scanner(new File(Config.WORKOUTFILE))){
		  while(scanner.hasNextLine()) { 
			  ArrayList<String> row = getRecordFromLine(scanner.nextLine());
			  w1.addWorkout(row.get(0), Workouts.Equipment.valueOf(row.get(1)), Workouts.Muscle.valueOf(row.get(2)), 
					  Workouts.Muscle.valueOf(row.get(3)), row.get(4), row.get(5));
		  }
	  } 
	  catch (java.lang.IllegalArgumentException e) {
		  System.out.println("The input you gave is not found in the enum lists.");
		  e.printStackTrace();
	} catch (FileNotFoundException e1) {
		  System.out.println("The file "+Config.WORKOUTFILE+" does not exist.");
		// TODO Auto-generated catch block
		  e1.printStackTrace();
	}
  
	  
	  return w1;
  }

private static ArrayList<String> getRecordFromLine(String nextLine) {
	ArrayList<String> values = new ArrayList<String>();
	try (Scanner newScanner = new Scanner(nextLine)) {
		newScanner.useDelimiter(",");
		while(newScanner.hasNext()) {
			values.add(newScanner.next());
		}
	}
	return values;
	}
}