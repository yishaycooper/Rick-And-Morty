## Rick And Morty Project

This application demonstrates some of kotlin's functional paradigms
such as lambdas, scoped functions, mapping and filtering of data.

The fetching and parsing of date is done via Retrofit and moshi
Retrofit is a class utilized as a service and is lazy load in main activity,
moshi is a library imported into the Retrofit class
which can then be used to parse the JSON response as a kotlin object,
the kotlin objects are Character and CharacterResponse data classes,
the Character represents a single response entity, and the CharacterResponse
represents a list of all entities.

The response is then passed into an adapter which can then be loaded
into a recyclerView in the main activity.

To implement the master detail patter when clicking on an item
an onClick listener is added to every item in the inner view holder class
inside the onClick a detail activity is launched using Intents,
and because the Character and CharacterResponse implement the parcelable interface
they can then be passed and parsed as complex objects.

When navigating in onClick animation is also implement, to a achieve
this an Activity context is required because viewHolder is an inner class
and dose not inherit from Activity it is required to manually pass in a reference
from main activity to the adapter which will make it available in the inner viewHolder.

The navigation is side navigation defined in xml files and animates right in
and left out for better user experience.

Search functionality is added using the androidx SearchView

To conclude the pattern in this simple example Retrofit can be considered the controller
Character and CharacterResponse the model and main activity the view.

