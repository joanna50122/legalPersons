package legalpersons;

import java.util.HashSet;
import java.util.Set;
import logicalcollections.LogicalSet;

/**
 * Each instance of this class represents a node in a graph of legal persons and the ownership relation between them. 
 * 
 * @invar | getOwnedEntities() != null
 * @invar | getOwner() == null || getOwner().getOwnedEntities().contains(this)
 * @invar | getOwnedEntities().stream().allMatch(entity -> entity.getOwner() == this)
 */
public class LegalPerson {
	
	/**
	 * @invar | ownedEntities != null
	 * @invar | owner == null || owner.ownedEntities.contains(this)
	 * @invar | ownedEntities.stream().allMatch(entity -> entity.owner == this)
	 * 
	 * @peerObject
	 */
	private LegalPerson owner;
	/**
	 * @representationObject
	 * @peerObjects
	 */
	private Set<LegalPerson> ownedEntities = new HashSet<>();
	
	/**
	 * @peerObject
	 */
	public LegalPerson getOwner() { return owner; }
	
	/**
	 * @creates | result
	 * @peerObjects
	 */
	public Set<LegalPerson> getOwnedEntities() { return Set.copyOf(ownedEntities); }

	/**
	 * Initializes this object to represent a node with the given owner.
	 * 
	 * @mutates | owner
	 * @post | getOwner() == owner
	 * @post | owner == null || owner.getOwnedEntities().equals(LogicalSet.plus(old(owner.getOwnedEntities()), this))
	 */
	public LegalPerson(LegalPerson owner) {
		this.owner = owner;
		if (owner != null)
			owner.ownedEntities.add(this);
	}
	
	/**
	 * @pre | newOwner != null
	 * @pre | newOwner != getOwner()
	 * 
	 * @mutates | this, getOwner(), newOwner
	 * 
	 * @post | getOwner() == newOwner
	 * @post | old(getOwner()) == null || old(getOwner()).getOwnedEntities().equals(LogicalSet.minus(old(getOwner().getOwnedEntities()), this))
	 * @post | newOwner.getOwnedEntities().equals(LogicalSet.plus(old(newOwner.getOwnedEntities()), this))
	 */
	public void transferTo(LegalPerson newOwner) {
		if (owner != null)
			owner.ownedEntities.remove(this);
		owner = newOwner;
		owner.ownedEntities.add(this);
	}
	
}