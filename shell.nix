# Set up the project with nix
# cf https://github.com/Kpler/ct-webserver#option-2-using-nix
let
  jdk = pkgs.openjdk11;

  config = {
    packageOverrides = p: rec {
      sbt = p.sbt.overrideAttrs (
        old: rec {
          version = "1.6.2";

          src = builtins.fetchurl {
            url    = "https://github.com/sbt/sbt/releases/download/v${version}/sbt-${version}.tgz";
            sha256 = "0h6lmmpv6qnz0crx4700145lh4ibwdhl0dndcamh9yp6qjv3fxk3";
          };

          patchPhase = ''
            echo -java-home ${jdk} >> conf/sbtopts
          '';
        }
      );
    };
  };

  nixpkgs = builtins.fetchTarball {
    name   = "nixos-21.05";
    url    = "https://github.com/NixOS/nixpkgs/archive/1f91fd104066.tar.gz";
    sha256 = "1lcfcwgal9fpaiq71981abyzz160r6nx1y4pyy1dnvaf951xkdcj";
  };

  pkgs = import nixpkgs { inherit config; };
in
  pkgs.mkShell {
    buildInputs = [
      jdk
      pkgs.sbt
      pkgs.pre-commit
    ];
  }
