#!/usr/bin/env bash

#!/usr/bin/env bash

docker run \
  --name virtual-dispatcher \
  --network host \
  -d \
  virtual-dispatcher:latest
