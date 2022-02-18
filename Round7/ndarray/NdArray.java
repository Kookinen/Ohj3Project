/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
import java.util.AbstractCollection;
import java.lang.NegativeArraySizeException;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

public class NdArray<E> extends AbstractCollection<E> implements Iterable<E>{
    private int dimensions;
    private ArrayList<Integer> dimSizes;
    private Object[] array;
    private int ArrSize;
    
    public NdArray(Integer firstDimLen, Integer ...furtherDimLens){
        
        int count = 0;
        dimSizes = new ArrayList<>();
        if(firstDimLen<0){
            throw new NegativeArraySizeException(String.format("Illegal dimension size %d", firstDimLen));
        }
        else{
            dimSizes.add(firstDimLen);
        }
       
        for(int i:furtherDimLens){
            if(i<0){
                throw new NegativeArraySizeException(String.format("Illegal dimension size %d.", i));
            }
            else{
                dimSizes.add(i);
            }
            count += 1;
        }
        dimensions = count+1;
        int sizeCount=0;
        
        if(dimensions == 1){
            sizeCount = dimSizes.get(0);
            
        }
        else if(dimensions == 0){
            sizeCount = 0;
            
        }
        else{
            sizeCount =dimSizes.get(0);
            for(int i=0;i<dimensions-1;i++){
                sizeCount = sizeCount*dimSizes.get(i+1);
                
            }
        }
        ArrSize = sizeCount;
        array = new Object[sizeCount];
        
        
        
        
    }
    
    @Override
    public int size(){
        return ArrSize;
    }
    @SuppressWarnings("unchecked")
    public E get(int... indices){
        if(indices.length != dimensions){
            throw new IllegalArgumentException(String.format("The array has %d "
                    + "dimensions but %d indices were given.",
                    dimensions, indices.length));
        }
        else{
            int counter = 0;
            int index=0;
            for(int i:indices){
                if(i < 0 || i >= dimSizes.get(counter)){
                    throw new IndexOutOfBoundsException(String.format("Illegal"
                            + " index %d for dimension %d of length %d.", i,
                            counter+1,dimSizes.get(counter)));
                }
                else{
                    if(counter == 0){
                        index += i*dimSizes.get(counter+1);
                    }
                    else if(counter == dimensions-1){
                        index += i;
                    }
                    else{
                        index = (index+i)*dimSizes.get(counter+1);
                    }
                }
                counter+=1;
            }
            return (E) array[index];
            
        }
    }
    
    public void set(E item, int... indices){
        if(indices.length != dimensions){
            throw new IllegalArgumentException(String.format("The array has %d "
                    + "dimensions but %d indices were given.",
                    dimensions, indices.length));
        }
        int counter = 0;
        int index=0;
        
        for(int i:indices){
            
            if(i < 0 || i >= dimSizes.get(counter)){
                throw new IndexOutOfBoundsException(String.format("Illegal"
                        + " index %d for dimension %d of length %d.", i,
                        counter+1,dimSizes.get(counter)));
            }
            else{
                if(counter == 0){
                    index = i*dimSizes.get(counter+1);
                }
                else if(counter == dimensions-1){
                    index = index+i;
                }
                else{
                    index = (index+i)*dimSizes.get(counter+1);
                }
            }
            counter+=1;
            
        }
        
        
        
        array[index] = item;
    }
    public int[] getDimensions(){
        int[] dims = dimSizes.stream().mapToInt(i -> i).toArray();
        return dims;
    }
    private class EIterator implements Iterator<E>{
        private int pos = 0;
        @Override
        public boolean hasNext() {
            return pos<ArrSize;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if(pos >= ArrSize){
                throw new NoSuchElementException("No more values in the array!");
            }
            return (E) array[pos++];
        }
        
    }
    @Override
    public Iterator<E> iterator() {
        return new EIterator();
    }
    
    
}
