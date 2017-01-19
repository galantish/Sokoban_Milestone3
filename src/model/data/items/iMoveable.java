package model.data.items;

/**
* The Interface iMoveable - Interface that holds all the function that an movable
* item should implement.
*/
public interface iMoveable extends iGeneralItem
{
	public void Move(Position newPos);
}
