#!/bin/bash

# This script creates the google-services.json and GoogleService-Info.plist files
# from the GOOGLE_SERVICES_JSON and GOOGLE_SERVICES_PLIST environment variables.

set -e

if [ -z "$GOOGLE_SERVICES_JSON" ]; then
  echo "GOOGLE_SERVICES_JSON environment variable not set."
  exit 1
fi

if [ -z "$GOOGLE_SERVICES_PLIST" ]; then
  echo "GOOGLE_SERVICES_PLIST environment variable not set."
  exit 1
fi

echo "Creating google-services.json..."
echo "$GOOGLE_SERVICES_JSON" | base64 -d > ../androidApp/google-services.json

echo "Creating GoogleService-Info.plist..."
echo "$GOOGLE_SERVICES_PLIST" | base64 -d > ../iosApp/GoogleService-Info.plist

echo "Done."
