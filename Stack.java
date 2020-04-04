public class Stack{
    private int[] list;
    private int listSize;
    private Stack nextStack;
    public Stack(){
        list = new int[2];//initializes the stack to size 2 initially
        listSize = 0; //no elements in Stack yet
        nextStack = null; //finds the next stack to push an element into
    }
    private void resize(){//resizes the array if there are more elements than it can hold
        if(list.length == listSize){
            int[] temp = list;
            list = new int[temp.length*2];//doubles the array size to accomodate more values efficiently
            for(int i = 0; i < temp.length; i++){
                list[i] = temp[i];
            }
        }
    }

    public void createList(int[] listValues){
        if(listValues.length > 1){//sorts the list into max -> min order for the push/pull to work effectively
            for(int i = 0; i < listValues.length; i++){//SELECTION SORT
                int maxVal = listValues[i];
                int maxPos = i;
                for(int j = i; j < listValues.length; j++){
                    if(listValues[j] >= maxVal){
                        maxVal = listValues[j];
                        maxPos = j;
                    }
                }
                listValues[maxPos] = listValues[i];
                listValues[i] = maxVal;
            }
        }

        for(int k = 0; k < listValues.length; k++){
            push(listValues[k]);//pushes the newly ordered array into the Stack
            if(k != 0){
                if(listValues[k] == listValues[k-1]){//if the newly added value is the same as the previous value
                    pull();//remove the value from the Stack
                }
            }
        }
    }

    public int[] getList(){//returns the array within the stack
        return list;
    }

    public void setNext(Stack next){//sets the next Stack that this Stack should push its elements into
        nextStack = next;
    }

    public Stack next(){//calls the next Stack
        return nextStack;
    }

    public boolean push(int val){//Pushes into the element to the top of the Stack
        if(listSize == 0){
            list[listSize] = val;//if there are no elements in the Stack then add the first one
            listSize += 1;
            return true;
        }

        if(val <= list[listSize-1]){//only adds the element if the element being added is smaller than the last one in Stack
            resize();//resize if the Stack array is too small
            list[listSize] = val;
            listSize += 1;
            return true;
        }
        else{
            return false;
        }
    }

    public int pull(){//Pulls (Removes) the top element of the Stack and returns it
        int temp = list[listSize-1];
        list[listSize-1] = 0;//Sets the element to 0
        listSize -= 1;//Size of the list is one less than previously
        return temp;
    }

    public int check(){//returns the very top element without removing it
        return list[listSize-1];
    }

    public boolean isEmpty(){//returns True if the Stack is empty
        if(listSize == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size(){//returns the size of the list
        return listSize;
    }
}