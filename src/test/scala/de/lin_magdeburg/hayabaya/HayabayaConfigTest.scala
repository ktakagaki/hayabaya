package de.lin_magdeburg.hayabaya

/**
  * Created by cain on 09/05/16.
  */
class HayabayaConfigTest extends BaseTest {

  behavior of "HayabayaConfigTest"

  it should "validateName" in {

    val ConfSettings = new HayabayaConfig()
    
    assert(ConfSettings.name === Some("InvalidNameGiven"))

  }

}
