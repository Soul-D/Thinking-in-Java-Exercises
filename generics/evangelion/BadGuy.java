package generics.evangelion;

public abstract class BadGuy extends StoryCharacter{
    @Override
    public String toString() {
        return super.toString() + " is a Bad guy";
    }
}
