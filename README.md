```
_  _ ____ ___ ____    ____ _  _ _  _    ____ ____ ____ ____ 
|\/| |___  |  |__| __ |___  \/  |\/| __ |    |  | |__/ |___ 
|  | |___  |  |  |    |___ _/\_ |  |    |___ |__| |  \ |___ 
 
```
# meta-exm-core

This layer provides recipes and images for building a minimal embedded
OS for Armaz block.


## Dependencies

This layer depends on:

```
URI: git://git.openembedded.org/bitbake
branch: master

URI: git://git.openembedded.org/openembedded-core
layers: meta
branch: master

URI: git://git.openembedded.org/meta-openembedded
layers: meta-oe, meta-python
branch: master
```


## Patches

Please submit any patches against the meta-exm-core layer to the
maintainer:

Maintainer: [Benoit Rapidel](mailto:benoit.rapidel+devs@exmachina.fr)


## Table of Contents

1. Adding the meta-exm-core layer to your build


### 1. Adding the meta-exm-core layer to your build

In order to use this layer, you need to make the build system aware of
it.

Assuming the exm-core layer exists at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the exm-core layer to bblayers.conf, along with any
other layers needed. e.g.:

```
BBLAYERS ?= " \
    /path/to/yocto/meta \
    /path/to/yocto/meta-yocto \
    /path/to/yocto/meta-yocto-bsp \
    /path/to/openembedded/meta-oe
    /path/to/openembedded/meta-python
    /path/to/yocto/meta-exm-core \
"
```