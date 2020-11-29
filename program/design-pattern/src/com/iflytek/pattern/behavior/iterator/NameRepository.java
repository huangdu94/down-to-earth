package com.iflytek.pattern.behavior.iterator;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:44
 */
public class NameRepository implements Container {
    private final String[] names = {"Robert", "John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}
