package legalpersons.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import legalpersons.LegalPerson;

class LegalPersonTest {

	@Test
	void test() {
		LegalPerson larryPage = new LegalPerson(null);
		assertEquals(null, larryPage.getOwner());
		assertEquals(Set.of(), larryPage.getOwnedEntities());
		
		LegalPerson alphabet = new LegalPerson(larryPage);
		assertEquals(larryPage, alphabet.getOwner());
		assertEquals(Set.of(), alphabet.getOwnedEntities());
		assertEquals(Set.of(alphabet), larryPage.getOwnedEntities());
		
		LegalPerson google = new LegalPerson(alphabet);
		assertEquals(alphabet, google.getOwner());
		assertEquals(Set.of(), google.getOwnedEntities());
		assertEquals(Set.of(google), alphabet.getOwnedEntities());
		assertEquals(Set.of(alphabet), larryPage.getOwnedEntities());
		
		LegalPerson stevejobs = new LegalPerson(null);
		google.transferTo(stevejobs);
		
	    // Verify the changes after the transfer
	    assertEquals(stevejobs, google.getOwner()); // 'google' should have 'newOwner' as its owner
	    assertEquals(Set.of(), google.getOwnedEntities()); // 'google' should not have any owned entities
	    assertEquals(Set.of(google), stevejobs.getOwnedEntities()); // 'newOwner' should own 'google'
	    assertEquals(Set.of(alphabet), larryPage.getOwnedEntities()); // 'larryPage' should still own 'alphabet'
	    assertEquals(Set.of(), alphabet.getOwnedEntities()); 
	    // Verify the state of the previous owner 'alphabet'
	    assertFalse(alphabet.getOwnedEntities().contains(google)); // 'alphabet' should no longer own 'google'
		
		
	}

}