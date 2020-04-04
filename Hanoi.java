public class Hanoi{
    /*
        This is a code version of solving the Tower of Hanoi where one can put in any number of Integer elements
        and visualize the computer transferring the values from the first tower to the last tower. This is done
        through a sequence and will return the current state of the towers after each move is made. The computer
        cannot move a larger value onto a smaller one.
        Note: this program will remove any duplicate Integers as it is designed for different sized numbers
    */
    private Stack startStack;//Three Stacks like the Three positions on the Tower of Hanoi
    private Stack midStack;
    private Stack lastStack;
    private int stackSize;
    private int[] stackList;

    public Hanoi(int[] startList){
        startStack = new Stack();//Initializes the three tower
        midStack = new Stack();
        lastStack = new Stack();
        startStack.createList(startList);//creates the stack within the first list
        stackList = startList;//holds the original ordered list
        stackSize = startStack.size();//holds the number of elements that we are working
        startStack.setNext(midStack);//sets the next available stack for each of the three stacks
        midStack.setNext(lastStack);
        lastStack.setNext(startStack);
    }

    public void transfer(){
        if(stackSize > 0){//if the list has at least one value
            if(stackSize % 2 == 0){//if the stack has an even number of elements
                sequence(stackSize);//only run the sequence method once (to get the values to the last tower)
            }
            else{//if the stack has an odd number of elements
                sequence(stackSize);//running the sequence once gets the values to the middle stack            
                sequence(stackSize);//but running it again gets it to the last tower
            }

        }
    }

    public void sequence(int n){//The recursive sequence the computer follows to solve the problem
        //the sequence is: an = (an-1), push the value of n to the next stack, then do (an-1) once more
        if(n != 0){//if n is not 0
            sequence(n-1);//go through this method again using n-1
            pushNextSpot(findStack(n));//push the top value of the stack that contains the n selection into the next available spot
            System.out.println(this);//shows the progression of moves in the terminal
            sequence(n-1);//go through this method once more using n-1
        }
    }

    public void pushNextSpot(Stack currStack){
        if(!currStack.next().isEmpty()){//if the next stack is not empty
            if(currStack.next().getList()[currStack.next().size()-1] < currStack.check()){//if the next stack has a value that is smaller than the value you are trying to push
                currStack.next().next().push(currStack.pull());//it should go in the stack after that so pull from current Stack and push to that Stack
            }
            else{//if the value you are trying to push is smaller or equal to the next stacks last value
                currStack.next().push(currStack.pull());//pull from current Stack and push to next Stack
            }
        }
        else{//if the next stack is empty
            currStack.next().push(currStack.pull());//pull from current stack and push to next stack
        }
    }

    public Stack findStack(int select){//returns the Stack that a value is in
        //select finds the requested spot in the original ordered stack list. Where 1 is the smallest value within the original stack values and so forth
        int val = stackList[stackSize-select];//finds the actual value called through select (again where 1 is the smallest value (top value) and so forth)
        if(!(startStack.isEmpty()) && startStack.check() == val){//if stack is not empty and it has the value we are looking for
            return startStack;//return the stack
        }
        else if(midStack.check() == val){//if the first stack did not contain the value then check if the middle stack does
            return midStack;
        }
        else{//if neither had the value then the final stack had the value
            return lastStack;
        }
    }

    public String toString(){//prints the three towers with each of their values
        String towerString = "";
        int[] towerOne = startStack.getList();//calls each towers current values
        int[] towerTwo = midStack.getList();
        int[] towerThree = lastStack.getList();
        for(int i = stackSize-1; i >= 0; i--){//starts at the end of the list (top of the stack) where the smallest number starts
            if(i < startStack.size()){//only prints out the number of elements that the stack contains
                towerString += towerOne[i] + "  ";
            }
            else{//if there are more values in another tower then starts printing out '-'
                towerString += "-  ";
            }
            
            if(i < midStack.size()){//Does the same for the middle tower
                towerString += towerTwo[i] + "  ";
            }
            else{
                towerString += "-  ";
            }

            if(i < lastStack.size()){//And for the last tower as well
                towerString += towerThree[i] + "  ";
            }
            else{
                towerString += "-  ";
            }

            towerString += "\n";
        }

        return towerString;
    }

    public static void main(String[] args){//Shows a testcase
        int[] testList = {1, 3, 4, 1, 2, 1, 5, 6, 7, 8, 9};
        Hanoi testHanoi = new Hanoi(testList);
        testHanoi.transfer();
    }
}

//NOTE: 
//I wanted to showcase a mistake I had made in the process of trying to find the Stack to pull the values from.
//I had interpreted the sequence wrong and tried to do something much more complex than necessary:

/*
    public Stack findStack(int selection){
        if(midStack.isEmpty() && lastStack.isEmpty()){
            return startStack;
        }

        if(selection == 1){
            int minVal;
            Stack minStack;

            if(!startStack.isEmpty()){
                minVal = startStack.check();
                minStack = startStack;
            }
            else{
                minVal = midStack.check();
                minStack = midStack;
            }

            if(!midStack.isEmpty() && minVal > midStack.check()){
                minVal = midStack.check();
                minStack = midStack;
            }

            if(!lastStack.isEmpty() && minVal > lastStack.check()){
                minVal = lastStack.check();
                minStack = lastStack;
            }
            return minStack;
        }
        else if(selection == 2){
            int minVal;
            Stack minStack;
            int maxVal; 
            Stack maxStack; 

            if(!startStack.isEmpty()){
                minVal = startStack.check();
                minStack = startStack;            
                maxVal = startStack.check();
                maxStack = startStack;

            }
            else{
                minVal = midStack.check();
                minStack = midStack;
                maxVal = midStack.check();
                maxStack = midStack;
            }

            if(!midStack.isEmpty() && minVal > midStack.check()){
                minVal = midStack.check();
                minStack = midStack;
            }
            if(!midStack.isEmpty() && maxVal < midStack.check()){
                maxVal = midStack.check();
                maxStack = midStack;
            }

            if(!lastStack.isEmpty() && minVal > lastStack.check()){
                minVal = lastStack.check();
                minStack = lastStack;
            } 
            if(!lastStack.isEmpty() && maxVal < lastStack.check()){
                maxVal = lastStack.check();
                maxStack = lastStack;
            }

            if(minStack.next() == maxStack || minStack.next().isEmpty()){
                return minStack.next().next();
            }
            else{
                return minStack.next();
            }

        }
        else{
            int maxVal; 
            Stack maxStack; 

            if(!startStack.isEmpty()){
                maxVal = startStack.check();
                maxStack = startStack;
            }
            else{
                maxVal = midStack.check();
                maxStack = midStack;
            }

            if(!midStack.isEmpty() && maxVal < midStack.check()){
                maxVal = midStack.check();
                maxStack = midStack;
            }

            if(!lastStack.isEmpty() && maxVal < lastStack.check()){
                maxVal = lastStack.check();
                maxStack = lastStack;
            }
            return maxStack;
        }
    }
*/