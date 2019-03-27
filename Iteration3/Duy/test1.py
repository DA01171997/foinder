import googlemaps
import pprint
from datetime import datetime
import sys

APIkey='KEY HERE'

gmaps = googlemaps.Client(key = APIkey)

if(len(sys.argv)==1):
	locationLL = '33.8749965 -117.884496462'
	print("Default Location: "+locationLL)
else:
	locationLL = str(sys.argv[1])+ ", " + str(sys.argv[2])
	print("Current Location: "+locationLL)
places_results = gmaps.places_nearby(location=locationLL, radius =1200, open_now=False, type = 'cafe')

#pprint.pprint(places_results)


for place in places_results['results']:
	my_place_id = place['place_id']
	
	my_fields = ['name', 'formatted_phone_number', 'type','adr_address', 'rating', 'review', 'price_level']

	place_details = gmaps.place(place_id = my_place_id, fields= my_fields)

	print(place_details)